package com.estudos.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 personGetFirstVersionOfPerson(){
        return new PersonV1("Martha");
    }

    @GetMapping("/v2/person")
    public PersonV2 personGetFirstVersionOfPersonV2(){
        Name name = new Name("Martha", "Albuquerque");

        return new PersonV2(name);
    }
}
