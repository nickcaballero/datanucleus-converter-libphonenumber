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

package org.datanucleus.samples;

import com.google.i18n.phonenumbers.Phonenumber;

import javax.jdo.annotations.*;

/**
 * Sample class of all converter formats
 *
 * @author nick
 * @since 8/14/14
 */
@PersistenceCapable
public class Sample {

    @PrimaryKey
    long id;

    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-international")
    Phonenumber.PhoneNumber internationalPhone;

    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-national")
    Phonenumber.PhoneNumber nationalPhone;

    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-e164")
    Phonenumber.PhoneNumber e164Phone;

    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-rfc3966")
    Phonenumber.PhoneNumber rfc3966Phone;

    public Sample(long id, Phonenumber.PhoneNumber internationalPhone, Phonenumber.PhoneNumber nationalPhone, Phonenumber.PhoneNumber e164Phone, Phonenumber.PhoneNumber rfc3966Phone) {
        this.id = id;
        this.internationalPhone = internationalPhone;
        this.nationalPhone = nationalPhone;
        this.e164Phone = e164Phone;
        this.rfc3966Phone = rfc3966Phone;
    }

    public long getId() {
        return id;
    }

    public Phonenumber.PhoneNumber getInternationalPhone() {
        return internationalPhone;
    }

    public Phonenumber.PhoneNumber getNationalPhone() {
        return nationalPhone;
    }

    public Phonenumber.PhoneNumber getE164Phone() {
        return e164Phone;
    }

    public Phonenumber.PhoneNumber getRfc3966Phone() {
        return rfc3966Phone;
    }
}
