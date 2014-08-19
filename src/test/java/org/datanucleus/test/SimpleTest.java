/*
 * Copyright (c) 2014 Nicolas Caballero. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.datanucleus.test;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.datanucleus.samples.Sample;
import org.junit.Assert;
import org.junit.Test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/**
 * Simple test for Sample class
 *
 * @author nick
 * @since 8/14/14
 */
public class SimpleTest {
    @Test
    public void testSimple() throws Exception {
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Test");
        PersistenceManager pm = pmf.getPersistenceManager();

        Phonenumber.PhoneNumber number = PhoneNumberUtil.getInstance().parse("+1-123-456-7890", null);
        Transaction tx = pm.currentTransaction();
        tx.begin();
        Sample sample = new Sample(1, number, number, number, null);
        pm.makePersistent(sample);
        tx.commit();

        Object objectId = pm.getObjectId(sample);
        pm.close();
        pmf.getDataStoreCache().evictAll();

        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        tx.begin();
        Sample returned = (Sample) pm.getObjectById(objectId);
        returned = pm.detachCopy(returned);
        tx.commit();
        pm.close();

        Assert.assertEquals(number, returned.getInternationalPhone());
        Assert.assertEquals(number, returned.getE164Phone());
        Assert.assertEquals(number, returned.getRfc3966Phone());
        Assert.assertNull(returned.getOtherPhone());
    }
}
