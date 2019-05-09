import { Component, OnInit, TemplateRef } from '@angular/core';
import {PatientDataService} from '../service/data/patient-data.service';
import {Router} from "@angular/router";
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

export class Patient {
  constructor(
    public firstName: string,
    public lastName: string,
    public gender: string,
  ) {

  }
}

@Component({
  selector: 'app-patient-data',
  templateUrl: './patient-data.component.html',
  styleUrls: ['./patient-data.component.css']
})
export class PatientDataComponent implements OnInit {

  patients = [];

  constructor(private router: Router, private patientDataService: PatientDataService, private modalService: BsModalService) { }

  ngOnInit() {
    this.patientDataService.getAllpatients().subscribe(
      response => {
        console.log(response);
        this.patients = response;
      }
    );
  }

  addPatient() {
    this.router.navigate(['patient']);
  }


  //////////////////////////////////////////////////////////////////////////////////////////

  private stompClient = null;
  patient;
  heartRate;
  bp;
  ecg;
  greetings: string[] = new Array();
  modalRef: BsModalRef;
  disabled = true;

  setConnected(connected: boolean) {
    this.disabled = !connected;
 
    if (connected) {
      this.greetings = [];
    }
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
    
  }

  connect() {
    let that = this;
    that.stompClient = Stomp.Stomp.client("ws://localhost:9999/greeting");
    that.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe('/topic/heartRate', function (message) {
        console.log("Message: " + message);
        console.log("Message Body: " + message.body);
        that.greetings.push(message.body);
        that.heartRate = message.body;
      });
  
  
      that.stompClient.subscribe('/topic/ecg', function (message) {
        that.greetings.push(message.body);
        that.ecg = message.body;
      });
  
  
      that.stompClient.subscribe('/topic/bp', function (message) {
        that.greetings.push(message.body);
        that.bp = message.body;
      });
 
      let data = JSON.stringify({
        'name': 'Hari'
      })
      that.stompClient.send("/app/message", {}, data);
    });
  }

  disconnect() {
    this.modalRef.hide();
    console.log("HI disconnect");
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
 
    this.setConnected(false);
    console.log('Disconnected!');
  }


}
