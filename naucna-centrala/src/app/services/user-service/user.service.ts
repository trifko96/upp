import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  fetchUsers() {
    return this.httpClient.get('http://localhost:8080/user/fetch') as Observable<any>;
  }

  registerUser(user, taskId) {
    return this.httpClient.post('http://localhost:8080/register/post/'.concat(taskId), user) as Observable<any>;
  }

  loginUser(user) {
    return this.httpClient.post('http://localhost:8080/users/login', user) as Observable<any>;
  }
  logoutUser() {
    return this.httpClient.get('http://localhost:8080/users/logout') as Observable<any>;
  }
  approveReviewer(form, taskId) {
    return this.httpClient.post('http://localhost:8080/register/approveReviewer/'.concat(taskId), form) as Observable<any>;
  }
  activateUser(form, taskId, username) {
    return this.httpClient.post('http://localhost:8080/register/activateAccount/'.concat(taskId) + '/username/' + username, form) as Observable<any>;
  }
  loadReviewers() {
    return this.httpClient.get('http://localhost:8080/users/getAllReviewers') as Observable<any>;
  }
  loadEditors() {
    return this.httpClient.get('http://localhost:8080/users/getAllEditors') as Observable<any>;
  }
}
