import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { HttpClientModule, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { FormsModule, NgForm } from '@angular/forms';
declare var bootstrap: any;


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  public employees: Employee[] = [];
  editEmployee: Employee | null = null; // Property to store the employee being edited
  deleteEmployee: Employee | null = null; // Property to store the employee being deleted
  

  ngOnInit(){
      this.getEmployee();
  }

  constructor(private employeeService: EmployeeService) {}

  public getEmployee() : void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddEmployee(addForm : NgForm) : void {
    this.employeeService.addEmployees(addForm.value).subscribe(
      (response : Employee) => {
        console.log(response);
        this.getEmployee();
        addForm.reset(); // Reset the form
  
        // Close the modal
        const modalElement = document.getElementById('addEmployeeModal');
        if (modalElement) {
          const modalInstance = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
          modalInstance.hide();
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
        addForm.reset();
      }
    );
    
  }

  public onUpdateEmployee(employee : Employee) : void {
    this.employeeService.updateEmployees(employee).subscribe(
      (response : Employee) => {
        console.log(response);
        this.getEmployee();
        const modalElement = document.getElementById('updateEmployeeModal');
        if (modalElement) {
          const modalInstance = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
          modalInstance.hide();
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
    
  }

  public onDeleteEmployee(employeeId : number) : void {
    this.employeeService.deleteEmployees(employeeId).subscribe(
      (response : void) => {
        console.log(response);
        this.getEmployee();
        const modalElement = document.getElementById('deleteEmployeeModal');
        if (modalElement) {
          const modalInstance = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
          modalInstance.hide();
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
    
  }
  public confirmDeleteEmployee(): void {
    if (this.deleteEmployee && this.deleteEmployee.id !== undefined) {
      this.onDeleteEmployee(this.deleteEmployee.id);
    } else {
      console.error('Employee or Employee ID is null or undefined');
    }
  }
  
  public searchEmployee(key: string) : void {
    console.log();
    const results : Employee[] = [];
    for(const employee of this.employees){
      if(employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if(results.length === 0 || !key){
      this.getEmployee();
    }
  }


  public onOpenModal(employee: Employee | null, mode : string) : void {
    
    const modalId = {
      add : 'addEmployeeModal',
      edit : 'updateEmployeeModal',
      delete : 'deleteEmployeeModal'
    }[mode];
    
    if(mode === 'edit' && employee) {
      this.editEmployee = employee;
    }
    if(mode === 'delete' && employee){
      this.deleteEmployee = employee;
    }
    if (!modalId) {
      console.error('Invalid modal mode:', mode);
      return;
    }
    const modalElement = document.getElementById(modalId);
    if(modalElement){
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }

  }
}
