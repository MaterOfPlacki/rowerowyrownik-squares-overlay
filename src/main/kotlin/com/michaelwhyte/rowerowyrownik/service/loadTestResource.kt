package com.michaelwhyte.rowerowyrownik.service

import org.springframework.core.io.DefaultResourceLoader
import java.io.File

fun loadTestResource(path: String): File = DefaultResourceLoader().getResource("classpath:${path}").file