import  {Link} from "react-router-dom"

const Navigation = () => {
  return (
  <ul>
         <li><Link to="/">Home</Link></li>
         <li><Link to="/components">components</Link></li>
         <li><Link to="/counter">Counter</Link></li>
         <li><Link to="/calculator">Calculator</Link></li>
         <li><Link to="/generator">Generator</Link></li>
         <li><Link to="/account-manager">AccountManager</Link></li>
         <li><Link to="/bootstrap">BootStrap</Link></li>
         <li><Link to="/Todo">Todo</Link></li>
         <li><Link to="/feeds">Feeds</Link></li>
         <li><Link to="/contact">Contact</Link></li>


 </ul>
  );
};



export default Navigation;