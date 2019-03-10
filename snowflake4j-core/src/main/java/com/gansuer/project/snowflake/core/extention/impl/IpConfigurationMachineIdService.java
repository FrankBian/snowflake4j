package com.gansuer.project.snowflake.core.extention.impl;

import com.gansuer.project.snowflake.core.extention.MachineIdService;
import com.gansuer.project.snowflake.core.extention.model.MachineId;
import com.gansuer.project.snowflake.core.support.Precondition;
import com.gansuer.project.snowflake.core.util.IpUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;

@Slf4j
public class IpConfigurationMachineIdService implements MachineIdService {

    private static String hostIp;
    private static MachineId machineId;
    private static final AtomicBoolean inited = new AtomicBoolean(false);
    private final Map<String, MachineId> ipMaps = new HashMap<>();

    public IpConfigurationMachineIdService(String ipConfigs) {
        log.warn("begin to initialize IpConfigurationMachineIdService: configs={}", ipConfigs);
        init(ipConfigs);
        log.warn("IpConfigurationMachineIdService init {}", inited.get());
    }

    /**
     * @param configs format: ip1:dcId1:workerId1;ip2:dcId2:workerId2
     */
    private synchronized void init(String configs) {
        if (inited.get()) {
            log.warn("IpConfigurationMachineIdService has been inited");
            return;
        }
        if (StringUtils.isEmpty(configs)) {
            throw new IllegalArgumentException("IpConfiguration string is empty");
        }
        String[] arrs = StringUtils.split(configs, ";");
        if (Objects.isNull(arrs)) {
            throw new IllegalArgumentException("IpConfiguration string is empty");
        }
        Arrays.stream(arrs).map(e -> StringUtils.split(e, ":"))
            .filter(a -> a.length == 3)
            .filter(a -> InetAddressValidator.getInstance().isValid(a[0]))
            .filter(a -> StringUtils.isNumeric(a[1]) || StringUtils.isNumeric(a[2]))
            .forEach(arr -> {
                String ip = arr[0];
                Long dcId = Long.valueOf(arr[1]);
                Long workerId = Long.valueOf(arr[2]);
                try {
                    Precondition.preconditionDataCenterId(dcId);
                    Precondition.preconditionWorkerId(workerId);
                    ipMaps.put(ip, new MachineId(dcId, workerId));
                } catch (Exception e) {
                    log.error("ipConfiguration's ipString error", e);
                }

            });
        String hostIp = IpUtils.getHostIp();
        if (StringUtils.isEmpty(hostIp)) {
            String errMsg = "Failed to get host ip address, stop to initialize IpConfigurationMachineIdService";
            log.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
        MachineId machineId = ipMaps.get(hostIp);
        if (Objects.isNull(machineId)) {
            String errMsg = String.format("Failed to get machineId for %1$s, stop to initialize IpConfigurationMachineIdService", hostIp);
            log.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
        if (inited.compareAndSet(false, true)) {
            IpConfigurationMachineIdService.hostIp = hostIp;
            IpConfigurationMachineIdService.machineId = machineId;
        }
    }

    @Override public MachineId getMachineId() {
        return machineId;
    }
}
