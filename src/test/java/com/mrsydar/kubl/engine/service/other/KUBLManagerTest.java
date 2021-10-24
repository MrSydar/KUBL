package com.mrsydar.kubl.engine.service.other;

import com.mrsydar.kubl.engine.service.k360.K360Manager;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.mockito.Mockito.mock;

class KUBLManagerTest {
    private final String csvPath = "kubl_manager/UBER_KUBL_EXPORT.csv";
    private final String csvEmptyPath = "kubl_manager/UBER_KUBL_EXPORT_EMPTY.csv";
    private final String csvBadPath = "kubl_manager/UBER_KUBL_EXPORT_BAD.txt";

    @Test
    void loadInvoicesToK360EmptyCSV() {
        K360Manager k360ManagerMock = mock(K360Manager.class);
        KUBLManager kublManager = new KUBLManager(k360ManagerMock);

        Summary summary = kublManager.sendInvoices(csvEmptyPath);
    }
}