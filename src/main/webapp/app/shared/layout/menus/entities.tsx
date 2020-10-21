import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/profile-my-suffix">
      <Translate contentKey="global.menu.entities.profileMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/address-my-suffix">
      <Translate contentKey="global.menu.entities.addressMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/profile-type-my-suffix">
      <Translate contentKey="global.menu.entities.profileTypeMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/kyclevel-my-suffix">
      <Translate contentKey="global.menu.entities.kyclevelMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/biller-my-suffix">
      <Translate contentKey="global.menu.entities.billerMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/wallet-account-my-suffix">
      <Translate contentKey="global.menu.entities.walletAccountMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/scheme-my-suffix">
      <Translate contentKey="global.menu.entities.schemeMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/scheme-category-my-suffix">
      <Translate contentKey="global.menu.entities.schemeCategoryMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/payment-transaction-my-suffix">
      <Translate contentKey="global.menu.entities.paymentTransactionMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/biller-transaction-my-suffix">
      <Translate contentKey="global.menu.entities.billerTransactionMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/customersubscription-my-suffix">
      <Translate contentKey="global.menu.entities.customersubscriptionMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/biller-platform-my-suffix">
      <Translate contentKey="global.menu.entities.billerPlatformMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/biller-category-my-suffix">
      <Translate contentKey="global.menu.entities.billerCategoryMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/wallet-account-type-my-suffix">
      <Translate contentKey="global.menu.entities.walletAccountTypeMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/countrol-account-my-suffix">
      <Translate contentKey="global.menu.entities.countrolAccountMySuffix" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/double-entry-logger-my-suffix">
      <Translate contentKey="global.menu.entities.doubleEntryLoggerMySuffix" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
