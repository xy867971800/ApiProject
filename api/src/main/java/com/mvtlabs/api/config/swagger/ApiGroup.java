package com.mvtlabs.api.config.swagger;

/**
 * API分组。在方法上定义
 *
 * @author
 *
 */
public @interface ApiGroup {

    String[] groups() default {};//自定义api分组

}
