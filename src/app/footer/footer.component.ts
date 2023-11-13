import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  sitemapUlr: string = window.location.origin + "/sitemap.xml";

  constructor() {
  }

  ngOnInit(): void {
  }

  goToTop() {


    //var currentScroll = document.documentElement.scrollTop || document.body.scrollTop;
    //  if (currentScroll > 0) {
    //  window.requestAnimationFrame(smoothscroll);
    window.scrollTo(0, 0);//, currentScroll - (currentScroll / 8));
    // }
  }


}
