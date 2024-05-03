package com.estudos.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //URI VERSIONING
    @GetMapping("/v1/person")
    public PersonV1 personGetFirstVersionOfPerson() {
        return new PersonV1("Martha");
    }

    //URI VERSIONING
    @GetMapping("/v2/person")
    public PersonV2 personGetSecondVersionOfPersonV2() {
        Name name = new Name("Martha", "Albuquerque");

        return new PersonV2(name);
    }

    //REQUEST PARAMETER VERSIONING
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonParameter() {
        return new PersonV1("Martha");
    }

    //REQUEST PARAMETER VERSIONING
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonParameter() {
        return new PersonV2(new Name("Martha", "Albuquerque"));
    }

    //HEADERDS VERSIONING
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Martha");
    }

    //HEADERDS VERSIONING
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {

        return new PersonV2(new Name("Martha", "Albuquerque"));
    }

    //MEDIA TYPE VERSIONING
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Martha");
    }

    //MEDIA TYPE VERSIONING
    @GetMapping(path = "/person/header", headers = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Martha", "Albuquerque"));
    }
}
