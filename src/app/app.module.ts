import {NgOptimizedImage} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AboutComponent} from './about/about.component';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ContactComponent} from './contact/contact.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {HomeComponent} from './home/home.component';
import {NavigationComponent} from './navigation/navigation.component';
import {NavButtonComponent} from './shared/nav-button/nav-button.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavigationComponent,
    AboutComponent,
    ContactComponent,
    FooterComponent,
    HeaderComponent,
    NavButtonComponent,
  ],
  imports: [
    BrowserModule.withServerTransition({appId: 'serverApp'}),
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgOptimizedImage,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
}
