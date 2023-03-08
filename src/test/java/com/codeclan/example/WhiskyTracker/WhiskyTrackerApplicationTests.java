package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	DistilleryRepository distilleryRepo;
	@Autowired
	WhiskyRepository whiskyRepo;

	@Test
	public void canFindWhiskyByDistillery(){

		Distillery new_distillery = new Distillery("Sams Bottler", "South of France");
		distilleryRepo.save(new_distillery);

		Whisky fresh = new Whisky("Fresh", 2, 2020, new_distillery);
		whiskyRepo.save(fresh);
		new_distillery.addWhisky(fresh);

		assertEquals(new_distillery.getWhiskies().get(0).getName(), whiskyRepo.findByDistillery(new_distillery).get(0).getName());
	}
}
