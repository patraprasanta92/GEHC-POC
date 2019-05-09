import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(username, password) {
    if(username === 'prasanta' && password === 'prasanta') {
      sessionStorage.setItem('authenticateruser', username);
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticateruser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticateruser');
  }
}

