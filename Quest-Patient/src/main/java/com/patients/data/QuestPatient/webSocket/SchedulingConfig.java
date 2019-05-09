package com.patients.data.QuestPatient.webSocket;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
@EnableScheduling
public class SchedulingConfig {

	@Autowired
	SimpMessagingTemplate template ;
	private static DecimalFormat df = new DecimalFormat("0.00");
    boolean flag = false;
	Random objGenerator = new Random();

    @Scheduled(fixedRate = 1000)
    @MessageMapping("/message")
    public void sendValues() {
           //System.out.println("sendValues :"+msg);
           flag = false;
           //for (int i = 0; i < 10; i++) {
           while(true) {
                  if(!flag) {
                  double hbeat = Math.random() * 49 + 1;
                  double bpresure = Math.random() * 50 + 1;
                  double ecg = Math.random() * 53 + 1;
                  try {
                        Thread.sleep(500);
                  } catch (InterruptedException e) {
                        e.printStackTrace();
                  }
                  template.convertAndSend("/topic/heartRate", "Heart Beat :" + df.format(hbeat));
                  template.convertAndSend("/topic/bp", "BP :" + df.format(bpresure));
                  template.convertAndSend("/topic/ecg", "ECG :" + df.format(ecg));
                  }else {
                        break;
                  }
           }
    }
    
    
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
              Collection<Object> cl = event.getMessage().getHeaders().values();
              String status = cl.iterator().next().toString();
              System.out.println("Status :"+status);
              if(status.equalsIgnoreCase("DISCONNECT")) {
                     flag = true;
              }
       }
 
       @MessageExceptionHandler
       public String handleException(Throwable exception) {
    	   template.convertAndSend("/errors", exception.getMessage());
              return exception.getMessage();
       }
   
}
