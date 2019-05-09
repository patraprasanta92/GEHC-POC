import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Patient} from "../../patient-data/patient-data.component";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class PatientDataService {

  constructor(private http: HttpClient) { }

  getAllpatients() {
    return this.http.get<Patient[]>("http://localhost:9999/getPatients");
  }

  addPatient (patient: Patient) {
    this.http.post('http://localhost:9999/addPatient', {
      firstName: patient.firstName,
      lastName: patient.lastName,
      gender: patient.gender
    })
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log('Error occured');
        }
      );

    console.log("done");
   }
}
