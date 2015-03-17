package com.ht.b2attr.b2attr_service;

import java.text.MessageFormat;

/**
 * Schema for test spring AOP and DI
 * @author Cloud_team
 *
 */
public class SocketClient {

	private String ip;
	private int port;

	public SocketClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void listen() {
		System.out.println(MessageFormat.format(
				"start socket listening, ip={0}, port={1}", this.ip, this.port));
		

	}
	
	public void receive(String msg)
	{
		System.out.println(MessageFormat.format(
				"Received message={0} from socket, ip={1}, port={2}", msg, this.ip, this.port));
		
	}
	
	public void close()
	{		
		System.out.println(MessageFormat.format(
				"stop socket listening, ip={0}, port={1}", this.ip, this.port));
	}
}
