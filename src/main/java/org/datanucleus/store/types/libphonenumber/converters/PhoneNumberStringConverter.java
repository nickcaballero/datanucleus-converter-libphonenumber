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

package org.datanucleus.store.types.libphonenumber.converters;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.types.converters.TypeConverter;

/**
 * Base class for PhoneNumber string converter along with implementations for available formats.
 * Exposes default region constructor for region specific type converters
 *
 * @author nick
 * @since 8/14/14
 */
public abstract class PhoneNumberStringConverter implements TypeConverter<Phonenumber.PhoneNumber, String> {

    public static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    private final PhoneNumberUtil.PhoneNumberFormat format;
    private final String defaultRegion;

    protected PhoneNumberStringConverter(PhoneNumberUtil.PhoneNumberFormat format) {
        this(format, null);
    }

    protected PhoneNumberStringConverter(PhoneNumberUtil.PhoneNumberFormat format, String defaultRegion) {
        this.format = format;
        this.defaultRegion = defaultRegion;
    }

    @Override
    public String toDatastoreType(Phonenumber.PhoneNumber value) {
        return PHONE_NUMBER_UTIL.format(value, format);
    }

    @Override
    public Phonenumber.PhoneNumber toMemberType(String value) {
        try {
            return PHONE_NUMBER_UTIL.parse(value, defaultRegion);
        } catch (NumberParseException e) {
            throw new NucleusException("Unable to parse phone number", e);
        }
    }

    public static class International extends PhoneNumberStringConverter {
        public International() {
            super(PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        }

        @Override
        public String toDatastoreType(Phonenumber.PhoneNumber value) {
            return super.toDatastoreType(value);
        }

        @Override
        public Phonenumber.PhoneNumber toMemberType(String value) {
            return super.toMemberType(value);
        }
    }

    public static class E164 extends PhoneNumberStringConverter {
        public E164() {
            super(PhoneNumberUtil.PhoneNumberFormat.E164);
        }

        @Override
        public String toDatastoreType(Phonenumber.PhoneNumber value) {
            return super.toDatastoreType(value);
        }

        @Override
        public Phonenumber.PhoneNumber toMemberType(String value) {
            return super.toMemberType(value);
        }
    }

    public static class RFC3966 extends PhoneNumberStringConverter {
        public RFC3966() {
            super(PhoneNumberUtil.PhoneNumberFormat.RFC3966);
        }

        @Override
        public String toDatastoreType(Phonenumber.PhoneNumber value) {
            return super.toDatastoreType(value);
        }

        @Override
        public Phonenumber.PhoneNumber toMemberType(String value) {
            return super.toMemberType(value);
        }
    }
}
