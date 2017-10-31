package com.tuberculosis.service.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 从properties文件读取属性值。
 */
@Component
public class PropertyService {

    @Value("${image.address}")
    public String imageAddress;

    @Value("${image.url}")
    public String imageUrl;

}
