package thread;

import java.io.IOException;
import java.util.List;
import org.snmp4j.util.TreeEvent;
import snmp.SnmpPCT;

public class Rx extends Thread {

	String ip, oid, community;
	List<TreeEvent> events;
	

	public Rx(String ip, String oid, String community) {
		this.ip = ip;
		this.oid = oid;
		this.community = community;

	}

	public List<TreeEvent> doRx(String ip, String oid, String community) throws IOException {
		SnmpPCT snmpobj = new SnmpPCT();
		List<TreeEvent> events = snmpobj.doSnmpWalk(ip, community, oid);
		return events;
	}

	@Override
	public void run() {
		
		try {
			events = doRx(ip,oid,community);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TreeEvent> getValue(){
		return events;
	}
}
