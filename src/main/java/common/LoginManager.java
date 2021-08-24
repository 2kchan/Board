package common;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener {
	private static Hashtable loginUsers = new Hashtable();
	
	private LoginManager() {
		
	}
	
	private static class LazyHolder {
		private static final LoginManager INSTANC = new LoginManager();
	}
	
	public static LoginManager getInstanc() {
		return LoginManager.LazyHolder.INSTANC;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		loginUsers.put(event.getSession(), event.getName());
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		loginUsers.remove(event.getSession());
	}
	
	public void setSession(HttpSession session, int mber_seq) {
		session.setAttribute(Integer.toString(mber_seq), this);
	}
	
	public String getMemberSequence(HttpSession session) {
		
		return (String) loginUsers.get(session);
	}
	
	public void removeSession(String mber_seq) {
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while(e.hasMoreElements()) {
			session = (HttpSession) e.nextElement();
			if (loginUsers.get(session).equals(mber_seq)) {
				session.invalidate();
			}
		}
	}
}













