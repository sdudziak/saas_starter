import { HttpHeaders } from '@angular/common/http';

export const APP_JSON_HEADER = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
