import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {HardcodedAuthenticationService} from './hardcoded-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(private route: Router, private hardcodedAuthenticationService: HardcodedAuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.hardcodedAuthenticationService.isUserLoggedIn()) {
      return true;
    }
    this.route.navigate(['login']);
    return false;
  }
}