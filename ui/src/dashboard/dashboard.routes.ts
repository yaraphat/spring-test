import { Routes } from "@angular/router";
import { MainComponent } from "./layout/main/main.component";
import { StudentFormComponent } from "./components/student/student-form/student-form.component";
import { StudentListComponent } from "./components/student/student-list/student-list.component";

export const dashboardRoutes: Routes = [
    {
        path: '',
        component: MainComponent,
        children: [
            {
                path: 'student/form',
                component: StudentFormComponent
            },
            {
                path: 'student/list',
                component: StudentListComponent
            },
            // {
            //   path:'profile',
            //   component:ProfileComponent
            // },
            // {
            //   path:'course',
            //   component:CourseComponent
            // },
        ]
    }
];
