import './home.scss';

import React from 'react';
//import { Link } from 'react-router-dom';
//import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import { useHistory } from 'react-router-dom';
//import { IRootState } from 'app/shared/reducers';

export type IHomeProp = StateProps;

export const Home = (props: IHomeProp) => {
 // const { account } = props;
  const history:any = useHistory();
  //const component:object;
  return (
    <Row>
       <section className = "home-bg">
          <h2 className = "welcome-head" >Welcome to SpecsWallet Admin Panel</h2>
          <p className = "welcome-paragraph">Manage users, transaction, profiless etc. View metrics</p>
          <button onClick = {()=>{history.push("/login")}} className = "get-started-button">Get started</button>
       </section>
    </Row>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Home);
