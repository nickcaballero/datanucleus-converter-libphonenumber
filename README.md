datanucleus-libphonenumber
====================================

[![Build Status](https://travis-ci.org/nickcaballero/datanucleus-libphonenumber.svg?branch=master)](https://travis-ci.org/nickcaballero/datanucleus-libphonenumber)

TypeConverter for storing libphonenumber

This provides a DataNucleus TypeConverter plugin for formatting a phone number field and parsing it using https://code.google.com/p/libphonenumber/.

Different formatting types are supported through different type converters. See https://github.com/nickcaballero/datanucleus-libphonenumber/blob/master/src/main/resources/plugin.xml

### Usage
```java
public class Sample {
    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-international")
    Phonenumber.PhoneNumber internationalPhone;

    @Persistent(defaultFetchGroup = "true")
    @Extension(vendorName = "datanucleus", key = "type-converter-name", value = "libphonenumber-e164")
    Phonenumber.PhoneNumber e164Phone;
}
```
