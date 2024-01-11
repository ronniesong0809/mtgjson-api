package com.ronsong.mtgjsonapi.schedule;

import com.ronsong.mtgjsonapi.service.MtgJsonEtlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MtgJsonSchedule {
    private final MtgJsonEtlService mtgJsonEtlService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void schedule() {
        log.info("Start ETL...");
        mtgJsonEtlService.save();
    }
}
