import { Component, OnInit, inject } from '@angular/core';
import { ProceduresService } from '../services/procedures.service';
import { Procedure, Procedures } from '../../types';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../components/modal/modal.component';
import { ProcedureFormComponent } from '../components/form/procedure/procedure.component';
import { NgbModal, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastService } from '../services/toast.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ModalComponent, ProcedureFormComponent, CommonModule, NgbToastModule, NgbPaginationModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  private modalService = inject(NgbModal);

  constructor(private route: ActivatedRoute, private router: Router, private proceduresService: ProceduresService, private toastService: ToastService) { }

  isLoading = false;
  procedures: Procedure[] = [];
  total: number = 0;
  page: number = 1;
  limit: number = 10;

  ngOnInit() {
    this.route.queryParams.subscribe((params: Params): void => {
      this.page = +params['page'] ? +params['page'] : this.page;
      this.limit = +params['limit'] ? +params['limit'] : this.limit;
      this.fetchProcedures();
    });
  }

  fetchProcedures() {
    this.isLoading = true;
    this.proceduresService.getProcedures({ page: this.page - 1, perPage: this.limit })
      .pipe(catchError((err) => {
        this.isLoading = false;
        this.toastService.showDanger(err.message);
        return throwError(() => err);
      }))
      .subscribe((procedures: Procedures) => {
        this.procedures = procedures.content;
        this.total = procedures.totalElements;
        this.page = this.page;
        this.isLoading = false;
      });

  }

  onPageChange(page: number) {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: { page, limit: this.limit },
      queryParamsHandling: 'merge'
    });
  }

  onCreateNewProcedureModalOpen() {
    this.modalService.open(ProcedureFormComponent).result.then((result) => {
      if (result) {
        this.toastService.showSuccess("Meenetlus on lisatud");
        this.fetchProcedures();
      }
    }).catch((error) => {
      if (error !== undefined) {
        this.toastService.showDanger("Tekkis viga");
      }
    });
  }
}
