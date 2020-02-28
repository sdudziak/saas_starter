import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { APP_JSON_HEADER } from './common/utils/http-utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http
      .get('/api/anything', APP_JSON_HEADER)
      .subscribe(
        _ => this.title = 'fucking good',
        _ => this.title = 'fucking bad',
      );
  }
}
