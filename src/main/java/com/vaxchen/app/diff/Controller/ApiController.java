package com.vaxchen.app.diff.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaxchen.app.diff.Models.Data;
import com.vaxchen.app.diff.Repo.DataRepo;

@RestController
public class ApiController {
	
	@Autowired
	private DataRepo dataRepo;
	
	@GetMapping( value = "/v1/diff/1/left")
	public String putLeft(@RequestBody Data data) {
		dataRepo.save(data);
		return "left is saved";
	}
	
	@GetMapping( value = "/v1/diff/1/right")
	public String putRight(@RequestBody Data data) {
		dataRepo.save(data);
		return "right is saved";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping( value = "/v1/diff/1")
	public JSONObject getDiff(@RequestBody Data left, @RequestBody Data right) {
		JSONObject result = new JSONObject();
		
		//if size is not equal
		if(left.getContent().length()!=right.getContent().length())
			result.put("diffResultType", "SizeDoNotMatch");
		
		
		else if(left.getContent().length()==right.getContent().length()) {
			
			//if equal
			if(left.getContent().equals(right.getContent()))
				result.put("diffResultType", "Equal");
			
			//if same size but there is diff
			else {
				char[] s1 = left.getContent().toCharArray();
				char[] s2 = right.getContent().toCharArray();
				int i = 0, length = 0;
				
				result.put("diffResultType", "ContentDoNotMatch");
				
				JSONArray list = new JSONArray();
				while(i<s1.length) {
					if(s1[i]!=s2[i]) {
						length++;
					}
					
					else if(s1[i]==s2[i]&&length>0) {
						JSONObject diff = new JSONObject();
						diff.put("offset", i);
						diff.put("length", length);
						list.add(diff);
					}
					
				}
				result.put("diffs", list);
			}
			
		}
		dataRepo.deleteAll();
		return result;
	}
	
}
