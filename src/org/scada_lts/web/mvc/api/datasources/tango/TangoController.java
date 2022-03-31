package org.scada_lts.web.mvc.api.datasources.tango;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.serotonin.mango.Common;
import com.serotonin.mango.vo.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/datasources/tango")
public class TangoController {

    @PostMapping(value = "/deviceinfo")
    public ResponseEntity<Map<String, Object>> testPointLocator(
            @RequestBody ObjectNode data, HttpServletRequest request) {

        try {
            User user = Common.getUser(request);
            if(user != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("value", "Hello World!");
                response.put("response", "Hello World!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}