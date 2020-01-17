import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {
  categories = [];
  languages = [];
  books = [];

  constructor(private httpClient: HttpClient, private http: Http) { }

  startProcess() {
    return this.httpClient.get('http://localhost:8080/register/startProcess') as Observable<any>;
  }
  startMagazineProcess() {
    return this.httpClient.get('http://localhost:8080/magazine/startMagProcess') as Observable<any>;
  }
  createMagazine(data, taskId) {
    return this.httpClient.post('http://localhost:8080/magazine/create/'.concat(taskId), data) as Observable<any>;
  }

  getTask(processId: string) {
    return this.httpClient.get('http://localhost:8080/register/nextTask/'.concat(processId)) as Observable<any>;
  }
  getTaskMag(processId: string) {
    return this.httpClient.get('http://localhost:8080/magazine/nextTaskMag/'.concat(processId)) as Observable<any>;
  }
  getTasks(processInstance: string) {

    return this.httpClient.get('http://localhost:8080/register/get/tasks/'.concat(processInstance)) as Observable<any>;
  }
  getTasksOfUser(username: string) {
    return this.httpClient.get('http://localhost:8080/register/getTasksUser/'.concat(username)) as Observable<any>;
  }
  claimTask(taskId) {
    return this.httpClient.post('http://localhost:8080/register/tasks/claim/'.concat(taskId), null) as Observable<any>;
  }
  loadTask(taskId) {
    return this.httpClient.get('http://localhost:8080/register/loadTask/'.concat(taskId)) as Observable<any>;

  }
  completeTask(task) {
    if (task.name === 'Approve') {
      window.location.href = 'approveReviewer/' + task.taskId;
    } else {
        window.location.href = 'checkingMagazine/' + task.taskId;
    }
  }

}
