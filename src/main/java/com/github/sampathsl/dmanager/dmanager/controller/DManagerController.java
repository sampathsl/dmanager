package com.github.sampathsl.dmanager.dmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DManagerController {

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error Occurred!";

    /*@PostMapping("/students")
    public ResponseEntity<?> createDownloadTask(@Valid @RequestBody StudentDTO studentDTO,
                                           UriComponentsBuilder ucBuilder, Errors errors) throws Exception {

        logger.info("IN createStudent METHOD");
        if (errors.hasErrors()) {
            return getErrors(errors);
        }

        StudentDTO studentDTONew = studentServiceImpl.create(studentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/tasks/{id}").buildAndExpand(studentDTONew.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }*/

}
