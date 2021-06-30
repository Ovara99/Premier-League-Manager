import {Component} from '@angular/core';
import {faCaretDown} from '@fortawesome/free-solid-svg-icons';
import {faBars} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  faCaretDown = faCaretDown;
  faBars = faBars;

  navbar: any;
  public responsiveNav(): void {
    this.navbar = document.getElementById("responsiveNavbar");
    if (this.navbar.className === "navbar") {
      this.navbar.className += " responsive";
    } else {
      this.navbar.className = "navbar";
    }
  }

}

