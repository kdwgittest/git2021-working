import "./App.scss";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { Suspense, lazy } from "react";
import { Provider } from "react-redux"; // react 앱에 redux store를 제공해줌
import { store } from "./store"; // redux store

import Home from "./domain/home/Home";
import Profile from "./domain/profile/Profile";
import EventMessage from "./components/EventMessage";
import AlertStack from "./components/alert/AlertStack";
import Progress from "./components/progress/Progress";
// import ContactCreate from "./domain/ContactCreate";

// SPA(Single Page Application)
// : 페이지 파일이 1개, index.html
// : 특정 영역(Switch)에 컴포넌트(js)를 로딩함
// : 애플리케이션이 컴파일될 때 import한 컴포넌트가 같이 컴파일됨
//   -> 컴파일됐을 때 파일크기가 커짐, 초기 로딩할 때 시간 걸림

// Lazy-Loading 처리
// 컴포넌트를 방문하는 시점에 로딩함
// const Todo = lazy(() => import("./domain/todo/Todo"));
const Todo = lazy(() => import("./domain/todo/TodoInlineEdit"));
const Feed = lazy(() => import("./domain/feed/Feed"));
const Contact = lazy(() => import("./domain/contact/Contact"));
const ContactCreate = lazy(() => import("./domain/contact/ContactCreate"));
const ContactEdit = lazy(() => import("./domain/contact/ContactEdit"));
const ContactDetail = lazy(() => import("./domain/contact/ContactDetail"));

const Photo = lazy(() => import("./domain/photo/Photo"));
const PhotoCreate = lazy(() => import("./domain/photo/PhotoCreate"));
const PhotoDetail = lazy(() => import("./domain/photo/PhotoDetail"));
const PhotoEdit = lazy(() => import("./domain/photo/PhotoEdit"));

// React == 컴포넌트 개발 라이브러리
function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="mx-auto">
          <header className="app-bar position-fixed d-flex justify-content-end bg-primary shadow">
            <Profile />
          </header>
          <nav
            style={{ width: "200px", height: "100vh", top: "50px" }}
            className="drawer-menu position-fixed bg-light shadow-sm"
          >
            <h3 className="ms-2">MY WORKSPACE</h3>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/todos">Todo</Link>
              </li>
              <li>
                <Link to="/feeds">Feeds</Link>
              </li>
              <li>
                <Link to="/photos">Photos</Link>
              </li>
              <li>
                <Link to="/contact">Contact</Link>
              </li>
            </ul>
          </nav>
          <main className="content-container">
            <Suspense fallback={<div>Loading...</div>}>
              <Switch>
                <Route path="/" component={Home} exact />
                <Route path="/todos" component={Todo} />
                <Route path="/feeds" component={Feed} />
                <Route path="/contact" component={Contact} exact />
                <Route path="/contact/create/" component={ContactCreate} />
                <Route path="/contact/detail/:id" component={ContactDetail} />
                <Route path="/contact/edit/:id" component={ContactEdit} />

                <Route path="/photos" component={Photo} exact />
                <Route path="/photos/create" component={PhotoCreate} />
                <Route path="/photos/detail/:id" component={PhotoDetail} />
                <Route path="/photos/edit/:id" component={PhotoEdit} />
                {/* id라는 매개변수를 url 경로에 넘김, path parameter */}
              </Switch>
            </Suspense>

            <Progress />
            <AlertStack />
            <EventMessage />
          </main>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
