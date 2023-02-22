package com.test.exam.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author TranCung
 * @since 20/02/2023
 */

@Component
public class SystemConstant {

    @Value("${app.version}")
    public static String VERSION = "1";

    /**
     * prefix api with format : /api/v1
     */
    public static final String API_PREFIX = "/api/v1";
}
