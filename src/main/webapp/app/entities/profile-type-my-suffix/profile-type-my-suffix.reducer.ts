import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProfileTypeMySuffix, defaultValue } from 'app/shared/model/profile-type-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PROFILETYPE_LIST: 'profileType/FETCH_PROFILETYPE_LIST',
  FETCH_PROFILETYPE: 'profileType/FETCH_PROFILETYPE',
  CREATE_PROFILETYPE: 'profileType/CREATE_PROFILETYPE',
  UPDATE_PROFILETYPE: 'profileType/UPDATE_PROFILETYPE',
  DELETE_PROFILETYPE: 'profileType/DELETE_PROFILETYPE',
  RESET: 'profileType/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProfileTypeMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProfileTypeMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: ProfileTypeMySuffixState = initialState, action): ProfileTypeMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROFILETYPE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROFILETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROFILETYPE):
    case REQUEST(ACTION_TYPES.UPDATE_PROFILETYPE):
    case REQUEST(ACTION_TYPES.DELETE_PROFILETYPE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROFILETYPE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROFILETYPE):
    case FAILURE(ACTION_TYPES.CREATE_PROFILETYPE):
    case FAILURE(ACTION_TYPES.UPDATE_PROFILETYPE):
    case FAILURE(ACTION_TYPES.DELETE_PROFILETYPE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILETYPE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILETYPE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROFILETYPE):
    case SUCCESS(ACTION_TYPES.UPDATE_PROFILETYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROFILETYPE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/profile-types';

// Actions

export const getEntities: ICrudGetAllAction<IProfileTypeMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROFILETYPE_LIST,
  payload: axios.get<IProfileTypeMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProfileTypeMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROFILETYPE,
    payload: axios.get<IProfileTypeMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProfileTypeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROFILETYPE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProfileTypeMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROFILETYPE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProfileTypeMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROFILETYPE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
