import Main from './components/main/Main'
import UnderConstruction from './components/main/UnderConstruction'

export const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/constructor',
        name: 'Constructor',
        component: UnderConstruction
    }
]