package com.yundotech.mymvp.data;

public enum ComWarningType {
    NO_AUTHORIZATION,
    NETWORK_ERROR,
    ALREADY_EXIST,
    NOT_EXIST,
    NO_RESULT,
    DATA_TOO_LONG,  // string, number
    STRING_FORMAT_NOT_MATCH, // passwords need number + string + special, or sequence number needs 'YD' + numbers, and so on.
}
