import { Component, OnInit } from '@angular/core';
import {Patient} from "../patient-data/patient-data.component";
import { PatientDataService } from '../service/data/patient-data.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  firstname: string;
  lastname: string;
  gender: string;
  patient: Patient;

  constructor(private patientDataService: PatientDataService) { }

  ngOnInit() {
  }

  save(){
    this.patient = new Patient(this.firstname,this.lastname,this.gender);
    this.patientDataService.addPatient(this.patient);
    console.log(this.firstname);
    console.log(this.lastname);
    console.log(this.gender);

  }

}
