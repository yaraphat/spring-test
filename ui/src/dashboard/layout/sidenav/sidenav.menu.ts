export interface TreeNode {
    name: string;
    route?: string;
    children?: TreeNode[];
    premittedRoles?: string[];
}

export const TREE_DATA: TreeNode[] = [
    {
        name: 'Setup',
        children: [
            {
                name: 'Department',
                children: [
                    { name: 'Create New', route: '/dashboard/department/form', premittedRoles: ['ADMIN'] },
                    { name: 'View List', route: '/dashboard/department/list' }
                ],
            },
            {
                name: 'Course',
                children: [
                    { name: 'Create New', route: '/dashboard/course/form' },
                    { name: 'View List', route: '/dashboard/course/list' }
                ],
            },
        ],
    },
    {
        name: 'Data Entry',
        children: [
            {
                name: 'Student',
                children: [
                    { name: 'Create New', route: '/dashboard/student/form' },
                    { name: 'View List', route: '/dashboard/student/list' }
                ],
            },
        ],
    },
    {
        name: 'Report',
        children: [
            {
                name: 'Subject-Student',
                route: '/dashboard/report/subject-student',
            },
        ],
    },
];