package com.pifrans.multidatabasedynamic.controllers;

import com.pifrans.multidatabasedynamic.configurations.datasource.DataSourceContext;
import com.pifrans.multidatabasedynamic.configurations.datasource.DataSourceRouting;
import com.pifrans.multidatabasedynamic.entities.users.User;
import com.pifrans.multidatabasedynamic.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DatabaseController {
    private final UserRepository userRepository;
    private final DataSourceRouting dataSourceRouting;


    @GetMapping(value = "/users/{dataSource}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> users(@PathVariable("dataSource") String dataSource) {
        dataSourceRouting.setDataSources();

        DataSourceContext.set(dataSource);

        return userRepository.findAll();
    }
}
