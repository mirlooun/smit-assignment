import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProceduresService } from '../../../services/procedures.service';
import { CreateProcedureDto } from '../../../../types';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-procedure',
  templateUrl: './procedure.component.html',
  styleUrls: ['./procedure.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ]
})
export class ProcedureFormComponent {
  procedureForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private proceduresService: ProceduresService,
    public activeModal: NgbActiveModal
  ) {
    this.procedureForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      identityCode: ['', Validators.required],
      reason: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.procedureForm.valid) {
      const procedureData: CreateProcedureDto = this.procedureForm.value;
      this.proceduresService.save(procedureData).subscribe((res) => {
        this.activeModal.close(res);
      });
    }
  }
}
