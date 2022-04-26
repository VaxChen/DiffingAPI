package com.vaxchen.app.diff.Controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaxchen.app.diff.Models.Data;
import com.vaxchen.app.diff.Repo.DataRepo;

@RestController
public class ApiController {
	
	@Autowired
	public DataRepo dataRepo;
	
	@PostMapping( value = "/v1/diff/{id}/left", produces = "application/json" )
	public String putLeft(@RequestBody Data data) {
		data.setId(1);
		dataRepo.save(data);
		return "left is saved";
	}
	
	@PostMapping( value = "/v1/diff/{id}/right", produces = "application/json" )
	public String putRight(@RequestBody Data data) {
		data.setId(2);
		dataRepo.save(data);
		return "right is saved";
	}
	
	@GetMapping( value = "/data")
	public List<Data> getData(){
		return dataRepo.findAll();
	}
	
	@DeleteMapping( value = "/delete")
	public void deleteData() {
		dataRepo.deleteAll();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping( value = "/v1/diff/{id}")
	public JSONObject getDiff() {
		JSONObject result = new JSONObject();
		String leftS = dataRepo.getById(1).getdata();
		String rightS = dataRepo.getById(2).getdata();
		
		//if size is not equal
		if(leftS.length()!=rightS.length())
			result.put("diffResultType", "SizeDoNotMatch");
		
		
		else if(leftS.length()==rightS.length()) {
			
			//if equal
			if(leftS.equals(rightS))
				result.put("diffResultType", "Equal");
			
			//if same size but there is diff
			else {
				char[] s1 = leftS.toCharArray();
				char[] s2 = rightS.toCharArray();
				int i = 0, length = 0, offset = 0;
				
				result.put("diffResultType", "DataDoNotMatch");
				
				JSONArray list = new JSONArray();
				while(i<s1.length) {
					if(s1[i]!=s2[i]) {
						length++;
					}
					
					else if(s1[i]==s2[i]&&length>0) {
						JSONObject diff = new JSONObject();
						diff.put("offset", offset);
						diff.put("length", length);
						list.add(diff);
						length = 0;
						offset= 1;
					}
					else {
						offset++;
					}
					
					i++;
					
				}
				result.put("diffs", list);
			}
			
		}
		dataRepo.deleteAll();
		return result;
	}
	
}
