package com.servlet3.sample.auth.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.servlet3.sample.BaseTest;
import com.servlet3.sample.common.util.SeqMongoUtil;

public class AuthServiceImplTest extends BaseTest {
	
	@Autowired AuthService authService;

	@Test
	public void testAuthInfo() {
		DBObject auth = new BasicDBObject();
		DBObject user = new BasicDBObject();
		user.put("userId", "test");
		user.put("name", "Rhys Lee");
		user.put("age", "29");
		user.put("money", 1000000);
		user.put("level", 78);

		auth.put("key", SeqMongoUtil.getSeq("authInfo")+"");
		auth.put("user", user);
		
		String key = "";
		
		try {
			/*
			 * 최초 로그인 성공.. 인증정보를 MongoDB에 저장한다.
			 */
			authService.createAuthInfo(auth);
			
			/*
			 * 현재는 키로 인증정보를 받아올 수 없다. Exception("401")
			 */
			key = auth.get("key").toString();
			authService.getAuthInfo(key);
			
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
		}

		try {
			/*
			 * 키를 이용해서 인증정보를 생성한다. --> Cache에 넣어준다.
			 * 이후 비로소 인증정보에 접근할 수 있다.
			 */
			auth = authService.generateAuthInfo(key);
			key = auth.get("key").toString();
			
			/*
			 * 키의 상태가 사용중으로 바꼈는지 확인한다. 0:최초생성, 1:사용중, 3:재발급폐기, 9:로그아웃폐기
			 */
			assertEquals(1, auth.get("status"));
			
			/*
			 * 인증정보 생성은 1회만 가능하다. 두번째 요청시 Exception("401")
			 */
			auth = authService.generateAuthInfo(key);
			
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
			
			try {
				/*
				 * 키가 발급된 상황에서 언제든 인증정보를 요청할 수 있다.
				 */
				System.out.println(authService.getAuthInfo(key));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		String newKey = "", oldKey = "";
		try {
			/*
			 * 키 재발급 요청.
			 */
			auth = authService.reGenerateAuthInfo(key, SeqMongoUtil.getSeq("authInfo")+"");
			newKey = auth.get("key").toString();
			oldKey = auth.get("oldKey").toString();
			assertEquals(key, oldKey);
			
			/*
			 * 기존키는 더이상 사용할 수 없다.
			 */
			authService.getAuthInfo(oldKey);
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
		}
		
		try {
			/*
			 * 새로운 키로도 아직은 사용할 수 없다.
			 */
			authService.getAuthInfo(newKey);
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
		}
		
		try {
			/*
			 * 새로운 키를 이용해서 인증정보를 생성한다. --> Cache에 넣어준다.
			 * 이후 비로소 인증정보에 접근할 수 있다.
			 */
			auth = authService.generateAuthInfo(newKey);
			
			/*
			 * 키의 상태가 사용중으로 바꼈는지 확인한다. 0:최초생성, 1:사용중, 3:재발급폐기, 9:로그아웃폐기
			 */
			assertEquals(1, auth.get("status"));
			
			/*
			 * 인증정보 생성은 1회만 가능하다. 두번째 요청시 Exception("401")
			 */
			auth = authService.generateAuthInfo(newKey);
			
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
			
			try {
				/*
				 * 키가 발급된 상황에서 언제든 인증정보를 요청할 수 있다.
				 */
				System.out.println(authService.getAuthInfo(newKey));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		/*
		 * 1초지연.
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		try {
			/*
			 * 1초 지연 후에는 기존 키를 사용할 수 없다. 재발급 절차에 따라 재발급 로직을 수행해야한다.
			 * 테스트를 위해 1초로 설정함. 정책에 따라 적당한 시간설정 해서 사용하면 됨.
			 */
			auth = authService.getAuthInfo(newKey);
			
			System.out.println("This message will not print. There is Exception(402).");
			System.out.println("authInfo is : " + auth.toString());
		} catch (Exception e) {
			/*
			 * 402 --> 키 기간만료.
			 */
			assertEquals("402", e.getMessage());
		}
		
		try {
			/*
			 * 키 재발급 요청.
			 */
			auth = authService.reGenerateAuthInfo(newKey, SeqMongoUtil.getSeq("authInfo")+"");
			newKey = auth.get("key").toString();
			
			/*
			 * 재발급시 키 상태는 "3"
			 */
			assertEquals(3, auth.get("status"));
			
			/*
			 * 새로운 키를 이용해서 인증정보를 생성한다. --> Cache에 넣어준다.
			 * 이후 비로소 인증정보에 접근할 수 있다.
			 */
			auth = authService.generateAuthInfo(newKey);
			
			/*
			 * 재발급 완료시 키 상태는 "1"
			 */
			assertEquals(1, auth.get("status"));
			/*
			 * 키가 발급된 상황에서 언제든 인증정보를 요청할 수 있다.
			 */
			System.out.println(authService.getAuthInfo(newKey));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			authService.removeAuthInfo(newKey);
			/*
			 * 삭제됐으니까 Exception("401");
			 */
			System.out.println(authService.getAuthInfo(newKey));
		} catch (Exception e) {
			/*
			 * 401 --> 존재하지 않는 키.
			 */
			assertEquals("401", e.getMessage());
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAuthInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateAuthInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testReGenerateAuthInfo() {
		fail("Not yet implemented");
	}

}
