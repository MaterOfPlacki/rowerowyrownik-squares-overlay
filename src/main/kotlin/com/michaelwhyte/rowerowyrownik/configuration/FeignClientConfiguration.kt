package com.michaelwhyte.rowerowyrownik.configuration

import com.michaelwhyte.rowerowyrownik.client.FeignClientPackageMarker
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [FeignClientPackageMarker::class])
class FeignClientConfiguration