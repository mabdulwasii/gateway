package ng.com.systemspecs.apigateway.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.domain.User_;
import ng.com.systemspecs.apigateway.repository.UserRepository;
import ng.com.systemspecs.apigateway.security.AuthoritiesConstants;

@RestController
@RequestMapping("/api")
public class TestAPI {
	@Autowired
    private UserRepository userRepository;

	@GetMapping("/testing")
	public List<User> hello() {
		List<User> u = userRepository.findAll();
		return u;
	}
}
