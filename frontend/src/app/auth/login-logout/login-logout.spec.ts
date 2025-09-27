import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginLogout } from './login-logout';

describe('LoginLogout', () => {
  let component: LoginLogout;
  let fixture: ComponentFixture<LoginLogout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginLogout]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginLogout);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
