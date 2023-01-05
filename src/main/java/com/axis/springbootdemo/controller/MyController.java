package com.axis.springbootdemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class MyController {
	
	private static ArrayList<Cricketer> cricList;
	static {
		cricList = new ArrayList<>();
		cricList.add(new Cricketer(1011, "Suryakumar Yadav", 120, 68, 10, 6, 195.3));
		cricList.add(new Cricketer(1012, "MS Dhoni", 80, 30, 5, 8, 150.1));
		cricList.add(new Cricketer(1013, "Kane Williamson", 68, 40, 8, 2, 135.9));
		cricList.add(new Cricketer(1014, "Finn Allen", 55, 25, 5, 7, 170.25));
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello!!! First SpringBoot Project. Good Afternoon...";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to SpringBoot:)";
	}
	
	@GetMapping("/cricketer")
	public Cricketer getCricketer() {
		return new Cricketer(1011, "Suryakumar Yadav" , 117, 56, 5, 6, 180.5);
	}
	
	// GET ALL CRICKETERS
	@GetMapping("/cricketers")
	public ArrayList<Cricketer> getCricketers(){
		return cricList;
	}
	
	// GET CRICKETERS BY ID
	@GetMapping("/cricketer/{cricketerId}") // variable parameters are to be written in '{}' brackets
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck: cricList) {
			if(ck.getCricketerId()==cricketerId) {
				return ck; // return if id is found
			} 

		}
		return null; // return if id not found
	}
	
	// To post a cricketer or update the list
	@PostMapping("/cricketer") // URL
	
	// method to add cricketer by taking cricketer object as input
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer){
		cricList.add(cricketer); // add cricketer to cricList
		// return the response entity object as string with status
		return new ResponseEntity<String>("Cricketer added successfully...",HttpStatus.OK);
	}
	
	// Update request for a particular cricketer data
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer(@PathVariable int cricketerId,@RequestBody Cricketer updatedCricketer){
		if(cricketerId!=updatedCricketer.getCricketerId()) {
			return new ResponseEntity<String>("Cricketer ID's don't match!!!",HttpStatus.BAD_REQUEST);
		}
		
		int index = cricList.indexOf(updatedCricketer);
		
		if(index==-1) {
			return new ResponseEntity<String>("Cricketer with ID: "+cricketerId+"not found!!!",HttpStatus.NOT_FOUND);
		}
		else {
			cricList.get(index).setBalls(updatedCricketer.getBalls());
			cricList.get(index).setFours(updatedCricketer.getFours());
			cricList.get(index).setRunsScored(updatedCricketer.getRunsScored());
			cricList.get(index).setSixes(updatedCricketer.getSixes());
			cricList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
			return new ResponseEntity<String>("Cricketer with ID: "+cricketerId+" updated successfully!!!",HttpStatus.OK);
		}
	}
	
	// Delete request for a particular cricketer
	
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId){
		Cricketer cricketer = getCricketerById(cricketerId);
		if(cricketer==null) {
			return new ResponseEntity<String>("Cricketer with ID: "+cricketerId+" is not Found!!!",HttpStatus.NOT_FOUND);
		}
		else {
			cricList.remove(cricketer);
			return new ResponseEntity<String>("Cricketer with ID:"+cricketerId+" deleted successfully!!!",HttpStatus.OK);
		}
	}
}
