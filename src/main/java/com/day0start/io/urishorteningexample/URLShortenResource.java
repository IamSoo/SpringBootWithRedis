package com.day0start.io.urishorteningexample;


import com.google.common.hash.Hashing;
import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("/shorturl")
@RestController
public class URLShortenResource  {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/url/{id}")
    public String getShortUrl(@PathVariable String id){
        System.out.println("The input id is :" + id);
        if(id == null){
            throw new RuntimeException("Input short url can't be null");
        }
        return redisTemplate.opsForValue().get(id);
    }

    @PostMapping("/url/set")
    public String setLongSortUrlMapping(@RequestBody String longUrl){
        UrlValidator validator = new UrlValidator(new String[]{"http","https"});
        if(validator.isValid(longUrl)){
         String hashId =    Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
            System.out.println("The Id is : " + hashId);
            redisTemplate.opsForValue().set(hashId,longUrl);
            return hashId;
        }
        throw new RuntimeException("Invalid URL : "+ longUrl);
    }

}
