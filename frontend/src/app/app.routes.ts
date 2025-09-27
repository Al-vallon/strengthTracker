import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'login',
        loadComponent: () => import('../app/auth/login-logout/login-logout').then(m => m.LoginLogout)
    },
    {
        path: 'register',
        loadComponent: () => import('../app/auth/register/register').then(m => m.Register)
    }
];
