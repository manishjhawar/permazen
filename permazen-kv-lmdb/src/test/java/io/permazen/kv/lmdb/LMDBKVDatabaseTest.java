
/*
 * Copyright (C) 2015 Archie L. Cobbs. All rights reserved.
 */

package io.permazen.kv.lmdb;

import io.permazen.kv.KVDatabase;
import io.permazen.kv.test.KVDatabaseTest;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class LMDBKVDatabaseTest extends KVDatabaseTest {

    private ByteArrayLMDBKVDatabase lmdbKV;

    @BeforeClass(groups = "configure")
    @Parameters("lmdbDirPrefix")
    public void setLMDBDirPrefix(@Optional String lmdbDirPrefix) throws IOException {
        if (lmdbDirPrefix != null) {
            final File dir = File.createTempFile(lmdbDirPrefix, null);
            Assert.assertTrue(dir.delete());
            Assert.assertTrue(dir.mkdirs());
            dir.deleteOnExit();
            this.lmdbKV = new ByteArrayLMDBKVDatabase();
            this.lmdbKV.setDirectory(dir);
        }
    }

    @Override
    protected boolean supportsReadOnlyAfterDataAccess() {
        return false;
    }

    @Override
    protected boolean supportsMultipleWriteTransactions() {
        return false;
    }

    @Override
    protected boolean transactionsAreThreadSafe() {
        return false;
    }

    @Override
    protected KVDatabase getKVDatabase() {
        return this.lmdbKV;
    }
}
